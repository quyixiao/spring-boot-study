package ch.qos.logback.core.rolling;

import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.rolling.helper.ArchiveRemover;
import ch.qos.logback.core.rolling.helper.CompressionMode;
import ch.qos.logback.core.rolling.helper.FileFilterUtil;
import ch.qos.logback.core.rolling.helper.SizeAndTimeBasedArchiveRemover;
import ch.qos.logback.core.util.DefaultInvocationGate;
import ch.qos.logback.core.util.FileSize;
import ch.qos.logback.core.util.InvocationGate;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static ch.qos.logback.core.CoreConstants.MANUAL_URL_PREFIX;

@Slf4j
public class LzSizeAndTimeBasedFNATP<E> extends TimeBasedFileNamingAndTriggeringPolicyBase<E> {


    enum Usage {EMBEDDED, DIRECT}

    ;


    int currentPeriodsCounter = 0;
    FileSize maxFileSize;
    // String maxFileSizeAsString;

    long nextSizeCheck = 0;
    static String MISSING_INT_TOKEN = "Missing integer token, that is %i, in FileNamePattern [";
    static String MISSING_DATE_TOKEN = "Missing date token, that is %d, in FileNamePattern [";

    private final Usage usage;

    public LzSizeAndTimeBasedFNATP() {
        this(Usage.DIRECT);
    }

    public LzSizeAndTimeBasedFNATP(Usage usage) {
        this.usage = usage;
    }

    @Override
    public void start() {
        // we depend on certain fields having been initialized in super class
        super.start();

        if (usage == Usage.DIRECT) {
            addWarn(CoreConstants.SIZE_AND_TIME_BASED_FNATP_IS_DEPRECATED);
            addWarn("For more information see " + MANUAL_URL_PREFIX + "appenders.html#SizeAndTimeBasedRollingPolicy");
        }

        if (!super.isErrorFree())
            return;


        if (maxFileSize == null) {
            addError("maxFileSize property is mandatory.");
            withErrors();
        }

        if (!validateDateAndIntegerTokens()) {
            withErrors();
            return;
        }

        archiveRemover = createArchiveRemover();
        archiveRemover.setContext(context);

        // we need to get the correct value of currentPeriodsCounter.
        // usually the value is 0, unless the appender or the application
        // is stopped and restarted within the same period
        String regex = tbrp.fileNamePattern.toRegexForFixedDate(dateInCurrentPeriod);
        String stemRegex = FileFilterUtil.afterLastSlash(regex);

        computeCurrentPeriodsHighestCounterValue(stemRegex);

        if (isErrorFree()) {
            started = true;
        }
    }

    private boolean validateDateAndIntegerTokens() {
        boolean inError = false;
        if (tbrp.fileNamePattern.getIntegerTokenConverter() == null) {
            inError = true;
            addError(MISSING_INT_TOKEN + tbrp.fileNamePatternStr + "]");
            addError(CoreConstants.SEE_MISSING_INTEGER_TOKEN);
        }
        if (tbrp.fileNamePattern.getPrimaryDateTokenConverter() == null) {
            inError = true;
            addError(MISSING_DATE_TOKEN + tbrp.fileNamePatternStr + "]");
        }

        return !inError;
    }

    protected ArchiveRemover createArchiveRemover() {
        return new SizeAndTimeBasedArchiveRemover(tbrp.fileNamePattern, rc);
    }

    void computeCurrentPeriodsHighestCounterValue(final String stemRegex) {
        File file = new File(getCurrentPeriodsFileNameWithoutCompressionSuffix());
        File parentDir = file.getParentFile();

        File[] matchingFileArray = FileFilterUtil.filesInFolderMatchingStemRegex(parentDir, stemRegex);

        if (matchingFileArray == null || matchingFileArray.length == 0) {
            currentPeriodsCounter = 0;
            return;
        }
        currentPeriodsCounter = FileFilterUtil.findHighestCounter(matchingFileArray, stemRegex);

        // if parent raw file property is not null, then the next
        // counter is max found counter+1
        if (tbrp.getParentsRawFileProperty() != null || (tbrp.compressionMode != CompressionMode.NONE)) {
            // TODO test me
            currentPeriodsCounter++;
        }
    }

    InvocationGate invocationGate = new DefaultInvocationGate();

    @Override
    public boolean isTriggeringEvent(File activeFile, final E event) {

        long time = getCurrentTime();

        // first check for roll-over based on time
        if (time >= nextCheck) {
            Date dateInElapsedPeriod = dateInCurrentPeriod;
            elapsedPeriodsFileName = tbrp.fileNamePatternWithoutCompSuffix.convertMultipleArguments(dateInElapsedPeriod, currentPeriodsCounter);
            elapsedPeriodsFileName = getNewFileName(elapsedPeriodsFileName);
            currentPeriodsCounter = 0;
            setDateInCurrentPeriod(time);
            computeNextCheck();
            return true;
        }

        // next check for roll-over based on size
        if (invocationGate.isTooSoon(time)) {
            return false;
        }

        if (activeFile == null) {
            addWarn("activeFile == null");
            return false;
        }
        if (maxFileSize == null) {
            addWarn("maxFileSize = null");
            return false;
        }
        if (activeFile.length() >= maxFileSize.getSize()) {
            elapsedPeriodsFileName = tbrp.fileNamePatternWithoutCompSuffix.convertMultipleArguments(dateInCurrentPeriod, currentPeriodsCounter);
            elapsedPeriodsFileName = getNewFileName(elapsedPeriodsFileName);
            currentPeriodsCounter++;
            return true;
        }
        return false;
    }


    public static String getNewFileName(String elapsedPeriodsFileName) {
        SimpleDateFormat df = new SimpleDateFormat("HH.mm.ss.SSS");
        String elapsedPeriodStemAfter = FileFilterUtil.afterLastSlash(elapsedPeriodsFileName);
        String elapsedPeriodStemBefore = beforeLastSlash(elapsedPeriodsFileName);
        String bs[] = elapsedPeriodStemAfter.split("\\.");
        StringBuilder sb = new StringBuilder(elapsedPeriodStemBefore).append("/");
        for (int i = 0; i < bs.length - 1; i++) {
            sb.append(bs[i]).append(".");
        }
        sb.append(df.format(new Date())).append(".").append(bs[bs.length - 1]);
        log.info(" 创建新的日志文件名 " + sb.toString());
        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println(getNewFileName("/home/admin/logs/eb_api/logs/api-all.2021-06-19.0.log"));
    }

    public static String beforeLastSlash(String sregex) {
        int i = sregex.lastIndexOf('/');
        if (i == -1) {
            return sregex;
        } else {
            return sregex.substring(0, i);
        }
    }

    @Override
    public String getCurrentPeriodsFileNameWithoutCompressionSuffix() {
        return tbrp.fileNamePatternWithoutCompSuffix.convertMultipleArguments(dateInCurrentPeriod, currentPeriodsCounter);
    }

    public void setMaxFileSize(FileSize aMaxFileSize) {
        this.maxFileSize = aMaxFileSize;
    }

}

package ch.qos.logback.core.rolling;

import ch.qos.logback.core.util.FileSize;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class LzSizeAndTimeBasedRollingPolicy<E> extends TimeBasedRollingPolicy<E> {

    FileSize maxFileSize;

    @Override
    public void start() {
        LzSizeAndTimeBasedFNATP<E> sizeAndTimeBasedFNATP = new LzSizeAndTimeBasedFNATP<E>(LzSizeAndTimeBasedFNATP.Usage.EMBEDDED);
        if (maxFileSize == null) {
            addError("maxFileSize property is mandatory.");
            return;
        } else {
            addInfo("Archive files will be limited to [" + maxFileSize + "] each.");
        }

        sizeAndTimeBasedFNATP.setMaxFileSize(maxFileSize);
        timeBasedFileNamingAndTriggeringPolicy = sizeAndTimeBasedFNATP;

        if (!isUnboundedTotalSizeCap() && totalSizeCap.getSize() < maxFileSize.getSize()) {
            addError("totalSizeCap of [" + totalSizeCap + "] is smaller than maxFileSize [" + maxFileSize + "] which is non-sensical");
            return;
        }

        // most work is done by the parent
        super.start();
    }

}

package com.example.springbootstudy.process;

import org.springframework.beans.factory.config.YamlProcessor;
import org.springframework.boot.yaml.SpringProfileDocumentMatcher;
import org.springframework.core.io.Resource;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;
import org.yaml.snakeyaml.resolver.Resolver;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

public class Processor extends YamlProcessor {

    public Processor(Resource resource, String profile) {
        if (profile == null) {
            setMatchDefault(true);
            setDocumentMatchers(new SpringProfileDocumentMatcher());
        } else {
            setMatchDefault(false);
            setDocumentMatchers(new SpringProfileDocumentMatcher(profile));
        }
        setResources(resource);
    }

    @Override
    protected Yaml createYaml() {
        return new Yaml(new StrictMapAppenderConstructor(), new Representer(),
                new DumperOptions(), new Resolver() {
            @Override
            public void addImplicitResolver(Tag tag, Pattern regexp,
                                            String first) {
                if (tag == Tag.TIMESTAMP) {
                    return;
                }
                super.addImplicitResolver(tag, regexp, first);
            }
        });
    }

    public Map<String, Object> process() {
        final Map<String, Object> result = new LinkedHashMap<String, Object>();
        process(new MatchCallback() {
            @Override
            public void process(Properties properties, Map<String, Object> map) {
                result.putAll(getFlattenedMap(map));
            }
        });
        return result;
    }

}



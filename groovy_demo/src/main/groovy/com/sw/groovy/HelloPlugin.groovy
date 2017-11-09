package com.sw.groovy

import org.gradle.api.Plugin
import org.gradle.api.Project;

public class HelloPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.task('testTask') << {
            println "Hello gradle plugin"
        }
    }
}
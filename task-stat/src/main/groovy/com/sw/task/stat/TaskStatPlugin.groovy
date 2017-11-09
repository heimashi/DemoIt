package com.sw.task.stat

import org.gradle.api.Plugin
import org.gradle.api.Project;

public class TaskStatPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.gradle.addListener(new TaskStatListener())
    }
}
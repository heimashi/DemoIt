package com.sw.task.stat

import org.gradle.BuildListener
import org.gradle.BuildResult
import org.gradle.api.Task
import org.gradle.api.execution.TaskExecutionListener
import org.gradle.api.initialization.Settings
import org.gradle.api.invocation.Gradle
import org.gradle.api.tasks.TaskState

class TaskStatListener implements TaskExecutionListener, BuildListener {

    HashMap<String, Long> taskStartMap = new HashMap<>()
    HashMap<String, Long> taskEndMap = new HashMap<>()

    @Override
    void beforeExecute(Task task) {
        taskStartMap.put(task.path, System.nanoTime())
    }

    @Override
    void afterExecute(Task task, TaskState taskState) {
        taskEndMap.put(task.path, System.nanoTime())
    }

    @Override
    void buildFinished(BuildResult result) {
        println "All Tasks (>50ms):"
        def times = []
        for (taskName in taskStartMap.keySet()) {
            def s = taskStartMap.get(taskName)
            def e = taskEndMap.get(taskName)
            def cost = (e - s) / 1e6
            if (cost >= 50) {
                times.add([k: cost, v: taskName])
            }
        }
        times.sort { a, b ->
            a.k > b.k ? 1 : ((a.k == b.k) ? 0 : -1)
        }
        for (time in times) {
            printf "%15sms  %s\n", time.k, time.v
        }

    }

    @Override
    void buildStarted(Gradle gradle) {}

    @Override
    void projectsEvaluated(Gradle gradle) {}

    @Override
    void projectsLoaded(Gradle gradle) {}

    @Override
    void settingsEvaluated(Settings settings) {}
}
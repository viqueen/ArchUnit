package com.tngtech.archunit.htmlvisualization;

import java.io.File;
import java.util.Arrays;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.htmlvisualization.testjson.structure.complexinherit.ComplexClass1;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.EvaluationResult;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.Test;

public class VisualizerDemo {
    @Test
    public void build_visualization() {
        System.out.println("Building example visualization...");

        JavaClasses classes = new ClassFileImporter().importPackages("com.tngtech.archunit.htmlvisualization",
                "java.io", "com.google.common.io");

        ArchRule rule1 = ArchRuleDefinition.noClasses().should().callMethod(Object.class, "toString");
        EvaluationResult evaluationResult1 = rule1.evaluate(classes);

        ArchRule rule2 = ArchRuleDefinition.noClasses().should().callMethod(ComplexClass1.class, "sayHello");
        EvaluationResult evaluationResult2 = rule2.evaluate(classes);

        new Visualizer(classes,
                new File(new File(Visualizer.class.getResource("/").getFile()).getParentFile().getParentFile(),
                        "example-visualization/visualization.html")
        ).visualize(Arrays.asList(evaluationResult1, evaluationResult2));
    }
}

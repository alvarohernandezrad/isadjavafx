/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ehu.isad;

import org.junit.Test;

import static jdk.nashorn.internal.codegen.LocalVariableTypesCalculator.assertNotNull;
import static org.junit.Assert.*;

public class AppTest {
    @Test public <App> void testAppHasAGreeting() {
        App classUnderTest = new App();
        assertNotNull("app should have a greeting", classUnderTest.getGreeting());
    }
}

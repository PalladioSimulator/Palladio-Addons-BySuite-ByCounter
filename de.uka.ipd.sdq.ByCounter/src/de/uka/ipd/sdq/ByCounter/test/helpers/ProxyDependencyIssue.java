package de.uka.ipd.sdq.ByCounter.test.helpers;

/**
 * This class demonstrates execution of a dependency class method, 
 * i.e. a method on an unknown instance of a known class or a subclass.
 */
public class ProxyDependencyIssue {
    ProxyDependency dep;

    public void setDependency(ProxyDependency dep) {
        this.dep = dep;
    }

    // This Method should be measured using ByCounter
    public void doSomething() {
        dep.calculate();
    }
}
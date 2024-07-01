package me.yevgnenll.lox.study;

public abstract class Pastry {
    abstract void accept(PastryVisitor pastryVisitor);
}

class Beignet extends Pastry {
    @Override
    void accept(PastryVisitor pastryVisitor) {
        pastryVisitor.visitBeignet(this);
    }
}

class Cruller extends Pastry {
    @Override
    void accept(PastryVisitor pastryVisitor) {
        pastryVisitor.visitCruller(this);
    }
}
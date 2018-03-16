package J3Lesson7;

class exploredClass extends Thread implements Runnable {
    public int i;
    private int p;

    public exploredClass(int i, int p) {
        this.i = i;
        this.p = p;
    }

    public int getI() {
        return i;
    }

    public int getP() {
        return p;
    }
}

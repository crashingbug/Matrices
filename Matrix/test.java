import Matrix.Matrix;

public class test {
    public static void main(String[] args) {
        Matrix a = new Matrix(4,4);
        a.display();
        Matrix.display(a.Inverse());
    }
}

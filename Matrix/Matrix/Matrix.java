package Matrix;
import java.util.Scanner;

class UnequvalentMatrices extends RuntimeException{
    UnequvalentMatrices(String s){
        super(s);
    }
}
class NonConformableMatrices extends RuntimeException{
    NonConformableMatrices(String s){
        super(s);
    }
}
class NonSquareMatrices extends RuntimeException{
    NonSquareMatrices(String s){
        super(s);
    }
}
public class Matrix {
    double[][] matrix;
    public Matrix(int r,int c){
        matrix = new double[r][c];
        Scanner sc = new Scanner(System.in);
        for( int i=0;i<matrix.length;i++)
        for(int j=0; j<matrix[i].length;j++){
            System.out.println("Enter Element r"+i+" c"+j+":");
            matrix[i][j]=sc.nextInt();}
    }
    private Matrix(int r,int c,boolean a){
        matrix = new double[r][c];
    }
    public Matrix(double[][] a){
        matrix = a;
    }

    //display matrix
    public void display(){
        for( int i=0;i<matrix.length;i++){
        for(int j=0; j<matrix[i].length;j++)
        System.out.print(matrix[i][j]+"\t");
        System.out.println();}
    }
    public static void display(Matrix k){
         for( int i=0;i<k.matrix.length;i++){
        for(int j=0; j<k.matrix[i].length;j++)
        System.out.print(k.matrix[i][j]+"\t");
        System.out.println();}
    }
    
    public Matrix add(Matrix k) throws UnequvalentMatrices{
        Matrix a = new Matrix(this.rows(),this.columns(),true);

        if(this.rows()!=k.rows()||this.columns()!=k.columns())
            throw new UnequvalentMatrices("The Matrices are not equivalent");

        for( int i=0;i<matrix.length;i++)
        for(int j=0; j<matrix[i].length;j++){
            a.matrix[i][j]=this.matrix[i][j]+k.matrix[i][j];}
        return a;

    }

    public Matrix multiply(double k){
        Matrix a = new Matrix(this.rows(),this.columns(),true);
        for( int i=0;i<matrix.length;i++)
        for(int j=0; j<matrix[i].length;j++){
            a.matrix[i][j]=this.matrix[i][j]*k;}
        return a;

    }
    public Matrix Multiply(Matrix k) throws NonConformableMatrices{
        Matrix a = new Matrix(this.rows(),k.columns(),true);

        if(this.columns()!=k.rows()){
            throw new NonConformableMatrices("The Matrices are Non-Conformable");    
        }

        for( int i=0;i<this.rows();i++)
        for(int j=0; j<k.columns();j++){
            for(int z=0;z<this.columns();z++){
                a.matrix[i][j]+=(this.matrix[i][z]*k.matrix[z][j]);
            }
        }  
        return a; 
    }

    public Matrix Transpose(){
        Matrix a = new Matrix(this.columns(),this.rows());
        for( int i=0;i<matrix.length;i++)
        for(int j=0; j<matrix[i].length;j++){
            a.matrix[j][i]=matrix[i][j];}
        return a;
    }

    public Matrix Minor(int k,int l){
        Matrix a = new Matrix(this.rows()-1,this.columns()-1,false);
        
        for( int i=0,x=0;i<matrix.length;i++){
            if(i==k)
                continue;
        for(int j=0,y=0; j<matrix[i].length;j++){
            if(j==l)
                continue;
            a.matrix[x][y]=matrix[i][j];
            y+=1;
        }
            x+=1;
        }
        return a;
    }

    public double det()throws NonSquareMatrices{
        if(this.rows()!=this.columns())
            throw new NonSquareMatrices("It is not a Square Matrix");
        if(this.rows()==2)
        return (this.matrix[0][0]*this.matrix[1][1])-(this.matrix[1][0]*this.matrix[0][1]);
        else{
            double a=0;
            for (int i=0; i<this.rows();i++)
                a += Math.pow(-1,i)*this.Minor(0,i).det()*this.matrix[0][i];
            //n and n=2 are both either odd or even
        return a;
        }
    }

    public double CoFactor(int i,int j){
        return Math.pow(-1,i+j)*this.Minor(i,j).det();
         //n and n=2 are both either odd or even
    }

    public Matrix Adjoint() throws NonSquareMatrices{
        Matrix a = new Matrix(this.rows(), this.columns(),false);
        for( int i=0;i<matrix.length;i++)
        for(int j=0; j<matrix[i].length;j++){
            a.matrix[j][i]=this.CoFactor(i,j);}
        return a;
    }

    public Matrix Inverse(){
        return this.Adjoint().multiply(1/this.det());
        
    }

    //Access length
    public int rows(){
        return matrix.length;
    }
    public int columns(){
        return matrix[0].length;
    }
}

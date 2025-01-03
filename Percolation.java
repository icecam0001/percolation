
public class Percolation {
    private int[] id;
    private boolean[] open;
    private int[] size; 
    private int total;
    
    
    public Percolation(int n){
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than zero"); //Throw IllegalArgumentException if n is negative
        }
        int x = n*n+2;
        System.out.println("x = " + x);
        this.id = new int[x];
        this.size = new int[x];
        this.open = new boolean[x];
        this.total=n;
        for (int i = 0; i<x; i++){
            if (i<n+1){
                this.id[i]=1;
            } else if (i<x && i>=(n*(n-1))){
                this.id[i]=x-1;
            } else{
                this.id[i]=i;
            }
                

            this.size[i]=1;
            this.open[i]=false;
        }
        this.open[x-1] = true;
        this.open[0] = true;
    }
        
    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        checkRowCol(row, col);
        int index = row*total + col+1;
        this.open[index] =true;
        
        if (!(row==1)){
            if (this.isOpen(row-1, col)){
                union(row, col, row-1, col);
            }
        }
        if (col<this.total){
            if (this.isOpen(row, col+1)){
                union(row, col, row, col+1);
            }
        }
        if (row<this.total) {
            if (this.isOpen(row+1, col)){
                union(row, col, row+1, col);
            }
        }
        

        if (!(col==1)){
            if (this.isOpen(row, col-1)){
                union(row, col, row, col-1);
            }
        }
        
        
        
    }
        
    public void checkRowCol(int row, int col) {
        if (row <= 0 || row > this.total || col <= 0 || col > this.total){
            throw new IllegalArgumentException("row/col indices out of bounds");
        }
            
    }
    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        checkRowCol(row, col);
        int iterate = (row-1)*total + col;
        return this.open[iterate];
    }
        
    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        checkRowCol(row, col);
        int index = (row-1)*total + col+1;
        return !(this.open[index]);
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        
        int counter = 0;
        for (int i = 1; i<=(total*total); i++){
            if (this.open[i]){
                counter+=1;
            }
        }
        return counter;
    }

    // does the system percolate?
    public boolean percolates(){
        if (root(1,0)==root(total+1,0)){
            return true;
        } 
        return false;
    }
    //quick union-find with improvements and weighting to balance trees
    private void union(int row, int col, int rowb, int colb){
        checkRowCol(row, col);
        checkRowCol(rowb, colb);
        int roota = root(row, col);
        int rootb = root(rowb, colb);
        if (this.size[roota] >= this.size[rootb]) {
            this.id[rootb] = roota;
            this.size[roota]+=this.size[rootb];
        } 
        else {
            this.id[roota] = rootb;
            this.size[rootb]+=this.size[roota];
        }

    }
    //root with grandfather system to improve time complexity
    public int root(int row, int col) {
        
        int index = (row-1)*total + col;
        int topoftree;
        while (true) {
            if (this.id[index] == index){
                topoftree=index;
                break;
            } else{
                this.id[index] = this.id[this.id[index]];
                index = this.id[index];
            }
        }
        return topoftree;
    }

    // test client (optional)
    
    public static void main(String[] args){

    }
}
 


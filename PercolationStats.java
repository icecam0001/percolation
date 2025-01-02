public class PercolationStats {
    private double[] means;
    private int trials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        this.means = new double[trials];
        this.trials = trials;
        Percolation e = new Percolation(n);
        int max = n;
        int randomspaces;
        int total;
        int randomCol = 1 + (int)(Math.random() * max); //1 added for minimum number of rows
        int randomRow = 1 + (int)(Math.random() * max);
        for (int i = 0; i<trials; i++){
            e = new Percolation(n);
            while(!(e.percolates())){
                while (e.isOpen(randomRow, randomCol)) {
                    randomCol = 1 + (int)(Math.random() * max);
                    randomRow = 1 + (int)(Math.random() * max)
                }
                e.open(randomRow, randomCol);
    
            }
            this.means[i] = ((double) e.numberOfOpenSites()/(n*n));
        }
        
        //return the total / n^2 for each and have a while loop doing this several times initialize mean and other variables return them at end
    }

    // sample mean of percolation threshold
    public double mean() {
        double total = 0;
        int counter = 0;
        double totalsq = 0;
        for (double mean : this.means){
            total+= mean;
            counter +=1;
        }
        totalsq = total*total;
        return Math.sqrt(totalsq/counter);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        double off = 0;
        int counter =0;
        for (double mean : this.means){
            if (mean<= mean()){
                off+=mean()-mean;
            } else if (mean> mean()){
                off-=mean()-mean;
            }
            counter +=1;
        }
        return off/counter;

    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return mean()-1.96*stddev()/this.trials;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return mean()+1.96*stddev()/this.trials;
    }
    

   // test client (see below)
   public static void main(String[] args){
    
   }

}       
       
        
        
        
        //while percolation is false keep opening up random indexes between 1,1 and n,n
        //check if its false
        // then stop if it percolates and get the values
    
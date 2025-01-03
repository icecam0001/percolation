public class PercolationStats {
    private double[] means;
    private int trials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        this.means = new double[trials];
        this.trials = trials;
        Percolation e = new Percolation(n);
        int max = n-1;
    
        int randomCol = 1 + (int)(Math.random() * max); //1 added for minimum number of rows
        int randomRow = 1 + (int)(Math.random() * max);
        for (int i = 0; i<trials; i++){
            e = new Percolation(n);
            while (!e.percolates()) {
                randomCol = 1 + (int)(Math.random() * max);
                randomRow = 1 + (int)(Math.random() * max);
                if (!e.isOpen(randomRow, randomCol)) {
                    e.open(randomRow, randomCol);
                }
            }
            this.means[i] = ((double) e.numberOfOpenSites()/(n*n));
        }
        
        //return the total / n^2 for each and have a while loop doing this several times initialize mean and other variables return them at end
    }

    // sample mean of percolation threshold
    public double mean() {
        double total = 0;
        for (double mean : this.means){
            total += mean;
        }
        return total / trials;  // You already have trials stored
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
        // Run simulation
        PercolationStats stats = new PercolationStats(300, 100); // 200x200 grid, 100 trials
        System.out.println("Mean: " + stats.mean());
        System.out.println("95% confidence interval: [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
   }

}       

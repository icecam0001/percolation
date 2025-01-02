# Percolation Assignment

## Project Structure
This project consists of two Java classes that together simulate and analyze percolation systems:

### 1. Percolation.java
A class that creates an n-by-n grid and simulates a percolation system. Sites can be opened, and the system percolates when there is a path of open sites from the top to bottom.

Key Methods:
- `Percolation(int n)` - Creates n-by-n grid with all sites blocked
- `void open(int row, int col)` - Opens site at (row, col) if not already open
- `boolean isOpen(int row, int col)` - Checks if site (row, col) is open
- `boolean isFull(int row, int col)` - Checks if site (row, col) is full
- `int numberOfOpenSites()` - Returns number of open sites
- `boolean percolates()` - Checks if system percolates

Implementation Details:
- Uses weighted quick union-find with path compression
- Includes virtual top and bottom nodes for efficient percolation detection
- Implements "grandfather" optimization in root finding

### 2. PercolationStats.java
A class that performs Monte Carlo simulation to estimate the percolation threshold.

Key Methods:
- `PercolationStats(int n, int trials)` - Performs trials independent experiments on n-by-n grid
- `double mean()` - Returns sample mean of percolation threshold
- `double stddev()` - Returns sample standard deviation of percolation threshold
- `double confidenceLo()` - Returns low endpoint of confidence interval
- `double confidenceHi()` - Returns high endpoint of confidence interval

## Usage Example
```java
// Run simulation
PercolationStats stats = new PercolationStats(200, 100); // 200x200 grid, 100 trials
System.out.println("Mean: " + stats.mean());
System.out.println("95% confidence interval: [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
```

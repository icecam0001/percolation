# Percolation System Implementation

## Overview
This project implements a percolation system simulator using weighted quick union-find with path compression. The system models a percolation process where sites in an n-by-n grid can be opened, and determines whether the system percolates (i.e., if there exists a path of open sites from top to bottom).

## Classes

### Percolation
The main class that implements the percolation system.

#### Key Features:
- Uses weighted quick union-find data structure for efficient connectivity checks
- Implements path compression ("grandfather") optimization for improved performance
- Virtual top and bottom nodes for efficient percolation detection
- O(log N) complexity for union operations

#### Main Methods:
- `open(row, col)`: Opens a site if it isn't already open
- `isOpen(row, col)`: Checks if a site is open
- `isFull(row, col)`: Checks if a site is full (connected to top)
- `percolates()`: Checks if the system percolates
- `numberOfOpenSites()`: Returns count of open sites

### PercolationStats
A Monte Carlo simulation to estimate percolation threshold.

#### Features:
- Runs multiple trials to determine average percolation threshold
- Calculates mean, standard deviation, and confidence intervals
- Random site selection for unbiased sampling

#### Statistical Methods:
- `mean()`: Calculates average percolation threshold
- `stddev()`: Computes standard deviation of thresholds
- `confidenceLo()/confidenceHi()`: Calculates 95% confidence interval

## Implementation Details

### Data Structures
- Main storage: Integer arrays for union-find (`id[]`)
- Size tracking: Weighted implementation (`size[]`)
- Site status: Boolean array (`open[]`)
- Virtual nodes: Top (index 1) and bottom (index n²+1) for percolation detection

### Optimizations
1. Weighted Quick Union
   - Keeps trees balanced by size
   - Ensures O(log N) height
   - Tracks subtree sizes for optimal merging

2. Path Compression
   - "Grandfather" linking during root finding
   - Flattens trees incrementally
   - Improves subsequent operations

### Monte Carlo Simulation
- Randomly opens sites until percolation occurs
- Tracks ratio of open sites to total sites
- Repeats for specified number of trials
- Calculates statistical measures across trials

## Usage
```java
// Create n-by-n percolation system
Percolation perc = new Percolation(n);

// Open sites
perc.open(row, col);

// Check if system percolates
boolean percolates = perc.percolates();

// Run statistical trials
PercolationStats stats = new PercolationStats(n, trials);
double mean = stats.mean();
double stddev = stats.stddev();
```

## Performance
- Union operations: O(log N)
- Find operations: O(log N) amortized
- Space complexity: O(N²)
- Monte Carlo trials: O(N² log N) per trial

## Dependencies
- Java Standard Library
- Random number generator for Monte Carlo simulation

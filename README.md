### Calculate two-dimensional sums
You're given an interface for a two-dimensional table of integers: 

```
public interface Table {
  // Set the cell at (x, y) to value.
  void set(int x, int y, int value);

  // Compute the sum of values from (0, 0) to (x, y), inclusive.
  int sum(int x, int y);
}
```

For example, if the table has this data (assuming 0-based origin at top-left after 9 calls to set()): 

```
1 2 3 
4 5 6 
7 8 9 
```

sum(0, 0) => 1 

sum(1, 1) => 1 + 2 + 4 + 5 => 12 

sum(2, 1) => 1 + 2 + 3 + 4 + 5 + 6 => 21 
and so on. 

Part 1: Implement this interface given that set() is called more often than sum().

Part 2: Implement this interface given that sum() is called more often than set().

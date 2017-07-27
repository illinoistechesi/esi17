# Battleship Genetic Algorithm

# Encoding

```
[0][1][2][3][4][5][6][7][8]

| i | Gene              | Range | Options |
|---|-------------------|-------|---------|
| 0 | Hull              | 0 - 9 | Value |
| 1 | Firepower         | 0 - 9 | Value |
| 2 | Speed             | 0 - 9 | Value |
| 3 | Range             | 0 - 9 | Value |
| 4 | Priority A        | 0 - 4 | {Distance, Health, Firepower, Speed, Range} |
| 5 | Priority A Sign   | 0 - 1 | {-1, +1} |
| 6 | Priority B        | 0 - 4 | {Distance, Health, Firepower, Speed, Range} |
| 7 | Priority B Sign   | 0 - 1 | {-1, +1} |
| 8 | Fire Pattern      | 0 - 1 | {Eliminate, Spread} |
```
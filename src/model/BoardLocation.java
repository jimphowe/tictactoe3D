package model;

public class BoardLocation {
  private int x;
  private int y;
  private int z;

  public BoardLocation(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public int getZ() {
    return this.z;
  }

  public boolean equals(BoardLocation other) {
    return other.x == this.x && other.y == this.y && other.z == this.z;
  }
}

package model;

class Move {
  int x,y,z;
  Direction dir;
  LocationState player;

  Move(int x, int y, int z, Direction dir, LocationState player) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.dir = dir;
    this.player = player;
  }
}

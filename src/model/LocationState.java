package model;

public enum LocationState {
  RED, WHITE, BLACK, EMPTY;

  @Override
  public String toString() {
    switch(this) {
      case RED: return " RED ";
      case WHITE: return "WHITE";
      case BLACK: return "BLACK";
      case EMPTY: return "EMPTY";
      default: throw new IllegalArgumentException();
    }
  }
}
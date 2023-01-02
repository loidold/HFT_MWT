package com.example.ghostDatabase.rest;

public class Ghost {

  private String name;
  private GhostType GhostType;

  private ThreadLevel threadLevel;

  public Ghost(String name, com.example.ghostDatabase.rest.GhostType ghostType, ThreadLevel threadLevel) {
    this.name = name;
    GhostType = ghostType;
    this.threadLevel = threadLevel;
  }

  public Ghost(){}

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public com.example.ghostDatabase.rest.GhostType getGhostType() {
    return GhostType;
  }

  public void setGhostType(com.example.ghostDatabase.rest.GhostType ghostType) {
    GhostType = ghostType;
  }

  public ThreadLevel getThreadLevel() {
    return threadLevel;
  }

  public void setThreadLevel(ThreadLevel threadLevel) {
    this.threadLevel = threadLevel;
  }
}

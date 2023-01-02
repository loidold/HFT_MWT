package com.example.ghostDatabase.rest;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spooky/")
public class SpookyController {

  Map <String,Ghost> ghosts = new HashMap<>();

  @GetMapping("/littlespook")
  public String littleSpook(){
    return "...boh";
  }

  @PostMapping("ghost")
  @ResponseStatus(HttpStatus.CREATED)
  public Ghost createGhost(@RequestBody Ghost ghost){
    ghosts.put(ghost.getName(),ghost);
    return ghost;
  }

  @GetMapping("ghost")
  @ResponseStatus(HttpStatus.OK)
  public Ghost getGhost(@RequestParam String name){
    return ghosts.get(name);
  }

  @GetMapping("ghosts")
  public List<Ghost> getAllGhosts(){
    return ghosts.values().stream().toList();
  }


  @PutMapping("ghost")
  public Ghost setGhost(@RequestBody Ghost ghost){
    //Ghost oldGhost = ghosts.get(ghost.getName());
    ghosts.put(ghost.getName(), ghost);
    return ghost;
  }

  @PutMapping("setThreadLevel")
  public Ghost setThreadLevel(@RequestParam String name, @RequestParam ThreadLevel threadLevel){
    Ghost ghost = ghosts.get(name);
    ghost.setThreadLevel(threadLevel);
    ghosts.put(name, ghost);
    return ghost;
  }


  @GetMapping("ghostType")
  public List<Ghost> getGhostByType(@RequestParam GhostType ghostType){
    return ghosts.values().stream().filter(ghost -> ghost.getGhostType()
                                                         .equals(ghostType))
                 .toList();
  }

  @DeleteMapping("ghost")
  public Ghost deleteGhost(@RequestParam String name){
    Ghost ghost = ghosts.get(name);
    ghosts.remove(name);
    return ghost;
  }

}

package fr.univ.dauphine.innov.message.controller;

import fr.univ.dauphine.innov.message.Models.Operation;
import fr.univ.dauphine.innov.message.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
public class OperationsController {

    @Autowired
    OperationRepository repository;

    @PostMapping(value = "/operations")
    public ResponseEntity<Operation> create(Principal principal, @RequestBody Operation operationBody, UriComponentsBuilder base) {
      Operation op = new Operation();
      long id = repository.count();
        op.setId_transaction(id+1);
        op.setTaux(operationBody.getTaux());
        op.setMonnaieDestination(operationBody.getMonnaieDestination());
        op.setMonnaieSource(operationBody.getMonnaieSource());
        op.setMontant(operationBody.getMontant());

        operationBody.setId_transaction(id);
        repository.save(op);
        URI location = base.path("/api/operations/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(location).body(operationBody);
    }
    @PutMapping(value = "/operations")
    public ResponseEntity<Operation> update(Principal principal, @RequestBody Operation operationBody, UriComponentsBuilder base) {

        repository.save(operationBody);
        URI location = base.path("/api/operations/{id}").buildAndExpand(operationBody.getId_transaction()).toUri();
        return ResponseEntity.created(location).body(operationBody);
    }


    @GetMapping(value = "/operations/{id}")
    public ResponseEntity getbyId(@PathVariable int  id) {
        Optional<Operation> op = repository.findById((long)id);
        if (op.isPresent())
            return ResponseEntity.ok(op.get());
        else
            return (ResponseEntity) ResponseEntity.status(-1);
    }

    @DeleteMapping(value = "/operations/{id}")
    public ResponseEntity<Integer> delete(@PathVariable int  id) {
        Operation op = repository.getOne((long)id);
        repository.delete(op);
        return ResponseEntity.ok(1);
    }

    @GetMapping(value = "/operations")
    public ResponseEntity<List<Operation>> getall() {

        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping(value = "/operationsHistory")
    public ResponseEntity<List<Operation>> getHistory(@RequestParam  String dest,@RequestParam String source) {
        List<Operation> operations = repository.findAll();
        operations.stream().filter(x -> x.getMonnaieSource().equals(source) && x.getMonnaieDestination().equals(dest)).collect(Collectors.toList());
        return ResponseEntity.ok(operations);
    }
}

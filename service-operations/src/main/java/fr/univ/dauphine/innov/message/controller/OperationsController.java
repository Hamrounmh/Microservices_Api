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


        Operation tx = repository.getOne(1L);
        return ResponseEntity.created(location).body(operationBody);
    }
    @PutMapping(value = "/operations")
    public ResponseEntity<Operation> update(Principal principal, @RequestBody Operation operationBody, UriComponentsBuilder base) {

        repository.save(operationBody);
        URI location = base.path("/api/operations/{id}").buildAndExpand(operationBody.getId_transaction()).toUri();
        return ResponseEntity.created(location).body(operationBody);
    }


    @GetMapping(value = "/operations/{id}")
    public ResponseEntity<Operation> getbyId(@PathVariable int  id) {
        return ResponseEntity.ok(repository.getOne((long)id));
    }

    @DeleteMapping(value = "/operations/{id}")
    public ResponseEntity<Operation> delete(@PathVariable int  id) {
        Operation op = repository.getOne((long)id);
        repository.delete(op);
        return ResponseEntity.ok(op);
    }

    @GetMapping(value = "/operations")
    public ResponseEntity<List<Operation>> getall() {

        return ResponseEntity.ok(repository.findAll());
    }
}

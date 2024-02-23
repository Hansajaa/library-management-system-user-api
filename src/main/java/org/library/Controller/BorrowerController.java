package org.library.Controller;

import lombok.extern.slf4j.Slf4j;
import org.library.Dto.BorrowerDto;
import org.library.Entity.BorrowerEntity;
import org.library.Response.BorrowerResponse;
import org.library.Service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrower")
@Slf4j
@CrossOrigin
public class BorrowerController {

    @Autowired
    BorrowerService borrowerService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addBorrower(@RequestBody BorrowerDto borrowerDto){
        boolean isSaved = borrowerService.addBorrower(borrowerDto);
        return isSaved ? ResponseEntity.ok(String.format("Saved %s",borrowerDto.getFirstName())) : ResponseEntity.badRequest().body("Not Saved");
    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<BorrowerEntity> getBorrwers(){
        try {
            Thread.sleep(1000);
        }catch(Exception e){
            e.printStackTrace();
        }

        return borrowerService.getBorrowers();
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BorrowerResponse deleteBorrower(@PathVariable Long id){
        boolean isDeleted = borrowerService.deleteBorrower(id);
        return isDeleted ? new BorrowerResponse(String.format("userId %d is Successfully Deleted",id)):new BorrowerResponse("Borrower Not Found");
    }

}

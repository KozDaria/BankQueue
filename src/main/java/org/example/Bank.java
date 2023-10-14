package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Bank {
    List<Branch> bankBranches;
    ObjectMapper objectMapper = new ObjectMapper();


    public void addBranchesList() throws IOException {
        File file = new File("src/test/resources/offices.txt");
        bankBranches = objectMapper.readValue(file, new TypeReference<List<Branch>>() {
        });
        System.out.println(bankBranches.get(0).getAdress());

    }

    public void addBranch(Branch branch) {
        bankBranches.add(branch);
    }

    public void removeBranch(Branch branch) {
        bankBranches.remove(branch);
    }

    public String branchWithMaxWorkload() {
        int maxWorkload = bankBranches.get(0).timeInQueue();
        String adressBranchWithMaxWorkload = bankBranches.get(0).getAdress();
        for (Branch branch : bankBranches) {
            if (branch.timeInQueue() > maxWorkload) {
                maxWorkload = branch.timeInQueue();
                adressBranchWithMaxWorkload = branch.getAdress();
            }
        }
        return adressBranchWithMaxWorkload;
    }

    public String branchWithMinWorkload() {
        int minWorkload = bankBranches.get(0).timeInQueue();
        String adressBranchWithMinWorkload = bankBranches.get(0).getAdress();
        for (Branch branch : bankBranches) {
            if (branch.timeInQueue() < minWorkload) {
                minWorkload = branch.timeInQueue();
                adressBranchWithMinWorkload = branch.getAdress();
            }
        }
        return adressBranchWithMinWorkload;
    }
}

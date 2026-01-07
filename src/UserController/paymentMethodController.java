/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserController;
import dao.paymentMethodDao;
import model.paymentMethodModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
/**
 *
 * @author hp
 */
public class paymentMethodController {
   private paymentMethodDao dao = new paymentMethodDao();

  // It must return 'paymentMethodModel', NOT 'byte[]' or 'void'
public paymentMethodModel getCompanyQR(int compId) {
        paymentMethodModel model = new paymentMethodModel();
        byte[] qrData = dao.getCompanyQR(compId);
        model.setCompanyQr(qrData);
        return model;
    }
// REMOVED 'String status' from the parameters list here
    /**
 * Processes payment and ensures the booking status is set to pending 
 * for both Cash and Online methods.
 */
public boolean processPayment(int bId, int cId, int compId, double amt, String method, String ref, java.io.File receiptFile, String status) {
    
    // STEP 1: Validation of Unique IDs
    // Check if any ID is zero or negative which would cause a database foreign key failure
    if (compId <= 0 || cId <= 0 || bId <= 0) {
        System.err.println("Controller Error: Invalid IDs - CompID: " + compId + ", CID: " + cId + ", BID: " + bId);
        return false; 
    }

    // STEP 2: Check for Duplicate Transaction Reference
    // Reference must be unique to prevent double-processing receipts
    if (!dao.isReferenceUnique(ref)) {
        System.err.println("Controller Error: Transaction Reference '" + ref + "' already exists in database.");
        return false; 
    }

    // STEP 3: Handle File Stream and Persistence
    // 'status' is received as "pending" from UI to force manual company approval
    try (java.io.InputStream is = (method.equalsIgnoreCase("online") && receiptFile != null && receiptFile.exists()) 
            ? new java.io.FileInputStream(receiptFile) : null) {

        // STEP 4: Create Model
        // Ensure your paymentMethodModel constructor is compiled to accept all these arguments
        paymentMethodModel model = new paymentMethodModel(bId, cId, compId, amt, method, ref, is, status);
        
        // STEP 5: Database Execution
        // This triggers the DAO which must update 'bookings' status to 'pending'
        boolean result = dao.savePayment(model);

        if (!result) {
            System.err.println("Controller Error: DAO failed to save payment record.");
        }
        
        return result;

    } catch (java.io.FileNotFoundException e) {
        System.err.println("Controller Error: Receipt file not found: " + e.getMessage());
        return false;
    } catch (java.io.IOException e) {
        System.err.println("Controller Error: IO Exception: " + e.getMessage());
        return false;
    } catch (Exception e) {
        // Catch-all to prevent the app from crashing; prints details to the console
        System.err.println("Controller Error: Critical Failure - " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}
}

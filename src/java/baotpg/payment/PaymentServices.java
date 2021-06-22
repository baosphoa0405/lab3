/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.payment;

import baotpg.books.BookDAO;
import baotpg.books.BookDTO;
import baotpg.histories.HistoryDTO;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class PaymentServices {

    private static final String CLIENT_ID = "AbtxE-67gnnLd9Pnn2l9oyB1G6o9FQQTjcvlaVLXvT-W4oASDmM2AmhjLLr-A7z27P54gTP0-oWp_kcO";
    private static final String CLIENT_SECRET = "EBsCq6mREcKWaR1DhvhnJAgjxrIBBs9I21skkg_ZKDoeItvU67KKxOJPtpxRWxMZOGhUyFcSL7EoQ1cK";
    private static final String MODE = "sandbox";

    public String authorizePayment(HistoryDTO history,  HashMap<String, Integer> cart,  Set<String> listKeys, int codeValue)
            throws PayPalRESTException {
        Payer payer = getPayerInformation();
        RedirectUrls redirectUrls = getRedirectURLs();
        BookDAO bookDao = new BookDAO();
        ArrayList<BookDTO> listBook = new ArrayList<>();
        try {
            listBook = bookDao.getListBook("", "", "", "");
        } catch (NamingException ex) {
            Logger.getLogger(PaymentServices.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PaymentServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Transaction> listTransaction = getTransactionInformation(history, cart, listKeys, listBook, codeValue);
        for (Transaction transaction : listTransaction) {
            System.out.println(transaction);
        }
        Payment requestPayment = new Payment();
        requestPayment.setTransactions(listTransaction);
        requestPayment.setRedirectUrls(redirectUrls);
        requestPayment.setPayer(payer);
        requestPayment.setIntent("authorize");

        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

        Payment approvedPayment = requestPayment.create(apiContext);

        return getApprovalLink(approvedPayment);

    }

    public Payment getPaymentDetails(String paymentId) throws PayPalRESTException {
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
        return Payment.get(apiContext, paymentId);
    }

    private Payer getPayerInformation() {
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName("John")
                .setLastName("Doe")
                .setEmail("sb-hbbjf4582809@personal.example.com");

        payer.setPayerInfo(payerInfo);

        return payer;
    }

    private RedirectUrls getRedirectURLs() {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8084/J3.L.P0018/cancel.html");
        redirectUrls.setReturnUrl("http://localhost:8084/J3.L.P0018/ReviewPayment");

        return redirectUrls;
    }

    private List<Transaction> getTransactionInformation(HistoryDTO history, HashMap<String, 
            Integer> cart,  Set<String> listKeys, ArrayList<BookDTO> listBook, int codeValue) {
        Details details = new Details();
        details.setSubtotal(String.valueOf(history.getTotalPrice()));
        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(String.valueOf(history.getTotalPrice()));
        amount.setDetails(details);
        
        Transaction transaction = new Transaction();
        transaction.setDescription("Bill payment book of Paypal");
        transaction.setAmount(amount);
        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<>();
        
        for (String key: listKeys) {
            for (BookDTO item : listBook) {
                if (item.getBookID().equals(key)) {
                    items.add(new Item(item.getTitle(), String.valueOf(cart.get(key)), 
                            String.valueOf((item.getPrice()*codeValue)/100), "USD"));
                }
            }
        }
        
        itemList.setItems(items);
        transaction.setItemList(itemList);

        List<Transaction> listTransaction = new ArrayList<>();
        listTransaction.add(transaction);

        return listTransaction;
    }

    private String getApprovalLink(Payment approvedPayment) {
        List<Links> links = approvedPayment.getLinks();
        String approvalLink = null;

        for (Links link : links) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                approvalLink = link.getHref();
                break;
            }
        }

        return approvalLink;
    }
}

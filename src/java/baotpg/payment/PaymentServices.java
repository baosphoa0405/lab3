/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.payment;

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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class PaymentServices {

    private static final String CLIENT_ID = "AbtxE-67gnnLd9Pnn2l9oyB1G6o9FQQTjcvlaVLXvT-W4oASDmM2AmhjLLr-A7z27P54gTP0-oWp_kcO";
    private static final String CLIENT_SECRET = "EBsCq6mREcKWaR1DhvhnJAgjxrIBBs9I21skkg_ZKDoeItvU67KKxOJPtpxRWxMZOGhUyFcSL7EoQ1cK";
    private static final String MODE = "sandbox";

    public String authorizePayment(HistoryDTO history)
            throws PayPalRESTException {

        Payer payer = getPayerInformation();
        RedirectUrls redirectUrls = getRedirectURLs();
        List<Transaction> listTransaction = getTransactionInformation(history);
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
        redirectUrls.setCancelUrl("http://localhost:8080/J3.L.P0018/cancel.html");
        redirectUrls.setReturnUrl("http://localhost:8080/J3.L.P0018/review_payment");

        return redirectUrls;
    }

    private List<Transaction> getTransactionInformation(HistoryDTO history) {
        Details details = new Details();
        LocalDate dateShip = history.getDateShip().toLocalDate();
        details.setShipping(dateShip.toString());

        Amount amount = new Amount();
        amount.setCurrency("USD");
//        Float.toString(history.getTotalPrice())
        amount.setTotal(String.format("%.2f", history.getTotalPrice()));
        amount.setDetails(details);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
//        transaction.setDescription(orderDetail.getProductName());

        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<>();

        Item item = new Item();
        item.setCurrency("USD");
        item.setName("haha");
        item.setPrice(Float.toString(history.getTotalPrice()));
        item.setQuantity("1");

        items.add(item);
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

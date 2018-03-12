package com.prasoonanand;

import javax.mail.*;
import javax.mail.search.AndTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by prasoonanand on 14/02/17.
 */
public class ReadMail {
    public static void checkMail(String username, String password, Date fromDate, Date toDate, String folder) {
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
        long time = System.currentTimeMillis();
        try {
            PrintWriter writer = new PrintWriter("emails.txt", "UTF-8");
            Session session = Session.getInstance(props, null);
            Store store = session.getStore();
            store.connect("imap.gmail.com", username, password);
            Folder inbox = store.getFolder(folder);
            inbox.open(Folder.READ_ONLY);
            SearchTerm olderThan = new ReceivedDateTerm(ComparisonTerm.LT, toDate);
            SearchTerm newerThan = new ReceivedDateTerm(ComparisonTerm.GT, fromDate);
            SearchTerm andTerm = new AndTerm(olderThan, newerThan);
            Message[] msgs = inbox.search(andTerm);
            //Message[] msgs = inbox.getMessages();
            Set<String> sets = new HashSet<String>();
            for (Message msg : msgs) {
                try {
                    Address[] in = msg.getFrom();
                    System.out.println(Arrays.toString(in));
                    for (Address address : in) {
                        sets.add(address.toString());
                    }
                    Address[] out = msg.getAllRecipients();
                    for (Address address : out) {
                        sets.add(address.toString());
                    }
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
            StringBuffer bf = new StringBuffer();
            for(String str: sets){
                String[] s = str.split("<");
                bf.append(s[0] + ",");
                try{
                    bf.append(s[1].replace(">", "") + "\n");
                }catch(Exception e){
                    bf.append("\n");
                }
            }
            writer.write(bf.toString());
            writer.close();
            // close folder and store (normally in a finally block)
            inbox.close(false);
            store.close();
            System.out.println("Time Taken : " + (System.currentTimeMillis() - time));
        } catch (Exception mex) {
            mex.printStackTrace();
        }
    }
}
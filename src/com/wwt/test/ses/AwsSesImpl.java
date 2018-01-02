package com.wwt.test.ses;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.simpleemail.AbstractAmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wwt
 * @date 2018/1/2 10:57
 */
public class AwsSesImpl {

    static final String FROM = "1327127023@qq.com";  // Replace with your "From" address. This address must be verified.
    static final String TO = "302746775@qq.com"; // Replace with a "To" address. If you have not yet requested
    // production access, this address must be verified.
    static final String BODY = "This email was sent through Amazon SES by using the AWS SDK for Java.";
    static final String SUBJECT = "Amazon SES test (AWS SDK for Java)";

    /*
     * Before running the code:
     *      Fill in your AWS access credentials in the provided credentials
     *      file template, and be sure to move the file to the default location
     *      (~/.aws/credentials) where the sample code will load the
     *      credentials from.
     *      https://console.aws.amazon.com/iam/home?#security_credential
     *
     * WARNING:
     *      To avoid accidental leakage of your credentials, DO NOT keep
     *      the credentials file in your source directory.
     */

    public static void main(String[] args) throws IOException {
        List<String> to = new ArrayList<String>();
        to.add(TO);
        MailTemplate mailTemplate = new MailTemplate(FROM,to,SUBJECT,"www.baidu.com");
        sendMail(mailTemplate);
    }

    public static String sendMail(MailTemplate mailTemplate){
        // Construct an object to contain the recipient address.
        Destination destination = new Destination().withToAddresses(mailTemplate.getTo());

        // Create the subject and body of the message.
        Content subject = new Content().withCharset("UTF-8").withData(mailTemplate.getSubject());
        Content textBody = new Content().withCharset("UTF-8").withData(mailTemplate.getBody());
        Body body = new Body().withText(textBody);
        // Create a message with the specified subject and body.
        Message message = new Message().withSubject(subject).withBody(body);

        // Assemble the email.
        SendEmailRequest request = new SendEmailRequest().withSource(mailTemplate.getFrom()).withDestination(destination).withMessage(message);

        try {
            System.out.println("Attempting to send an email through Amazon SES by using the AWS SDK for Java...");

            /*
             * The ProfileCredentialsProvider will return your [default]
             * credential profile by reading from the credentials file located at
             * (~/.aws/credentials).
             *
             * TransferManager manages a pool of threads, so we create a
             * single instance and share it throughout our application.
             */
            ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
            try {
                credentialsProvider.getCredentials();
            } catch (Exception e) {
                throw new AmazonClientException(
                        "Cannot load the credentials from the credential profiles file. " +
                                "Please make sure that your credentials file is at the correct " +
                                "location (~/.aws/credentials), and is in valid format.",
                        e);
            }

            // Instantiate an Amazon SES client, which will make the service call with the supplied AWS credentials.
            AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard()
                    .withCredentials(credentialsProvider)
                    // Choose the AWS region of the Amazon SES endpoint you want to connect to. Note that your production
                    // access status, sending limits, and Amazon SES identity-related settings are specific to a given
                    // AWS region, so be sure to select an AWS region in which you set up Amazon SES. Here, we are using
                    // the US East (N. Virginia) region. Examples of other regions that Amazon SES supports are US_WEST_2
                    // and EU_WEST_1. For a complete list, see http://docs.aws.amazon.com/ses/latest/DeveloperGuide/regions.html
                    .withRegion("us-east-1")
                    .build();

            // Send the email.
            client.sendEmail(request);
            System.out.println("Email sent!");
            return "Email sent!";
        } catch (Exception ex) {
            System.out.println("The email was not sent.");
            return "Error message: " + ex.getMessage();
        }
    }

}

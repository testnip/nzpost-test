package nz.co.nzpost.automation.page;

import com.codeborne.selenide.SelenideElement;
import nz.co.nzpost.automation.annotations.Page;
import nz.co.nzpost.automation.domain.SuccessDetails;
import nz.co.nzpost.automation.domain.TrackingNumber;

import static com.codeborne.selenide.Selenide.$;

@Page
public class ParcelForYouSuccessPage {

  public String getSubmittedTrackingNumber() {
    SuccessDetails successDetails = new SuccessDetails();
    SelenideElement submittedTrackingNumber = $(".success-info-ref-number");
    successDetails.setTrackingNumber(submittedTrackingNumber.getText());
    return successDetails.getTrackingNumber();
  }

  public String getSubmittedAddressDetails() {
    SuccessDetails successDetails = new SuccessDetails();
    SelenideElement submittedAddress = $(".success-info-redelivery-address");
    successDetails.setRedeliverAddress(submittedAddress.getText().replaceAll("\n", ""));
    return successDetails.getRedeliverAddress();
  }

  public String getSubmittedDateDetails() {
    SuccessDetails successDetails = new SuccessDetails();
    SelenideElement submittedDate = $(".success-info-redelivery-date");
    successDetails.setRedeliverDate(submittedDate.getText());
    return successDetails.getRedeliverDate();
  }
}


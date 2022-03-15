package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingResponsePojo {
    /*
      {
        "bookingid": 11,
                "booking": {
                            "firstname": "Ali",
                            "lastname": "Can",
                            "totalprice": 500,
                            "depositpaid": true,
                            "bookingdates": {
                                            "checkin": "2022-03-01",
                                            "checkout": "2022-03-11"
                                            }
                          }
      }
     */

    //1) private değişkenleri oluştur.
    private int bookingId;
    private BookingPojo booking;

    //2) getter ve setter

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public BookingPojo getBooking() {
        return booking;
    }

    public void setBooking(BookingPojo booking) {
        this.booking = booking;
    }


    //3) Parametreli ve Parametresiz Constructor

    public BookingResponsePojo() {
    }

    public BookingResponsePojo(int bookingId, BookingPojo booking) {
        this.bookingId = bookingId;
        this.booking = booking;
    }
    //4) toString()


    @Override
    public String toString() {
        return "BookingResponsePojo{" +
                "bookingId=" + bookingId +
                ", booking=" + booking +
                '}';
    }
}
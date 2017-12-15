package forester.jv.app.crypto;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * Created by FORESTER on 13.12.17.
 */
public class SecretKeyWrapper {

    private SecretKey cookieSecretKey;
    private Date cookieExpirationDate;
    private final long EXPIRATION_TIME_IN_MILLIS = 1000L*60L*1L;

    public SecretKeyWrapper(SecretKey cookieSecretKey) {
        this.cookieSecretKey = cookieSecretKey;
        this.cookieExpirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME_IN_MILLIS);
    }

    public SecretKey getCookieSecretKey() {
        return cookieSecretKey;
    }

    public void setCookieSecretKey(SecretKey cookieSecretKey) {
        this.cookieSecretKey = cookieSecretKey;
    }

    public Date getCookieExpirationDate() {
        return cookieExpirationDate;
    }

    public void setCookieExpirationDate(Date cookieExpirationDate) {
        this.cookieExpirationDate = cookieExpirationDate;
    }

    public void updateCookie(){
        cookieExpirationDate.setTime(cookieExpirationDate.getTime()+EXPIRATION_TIME_IN_MILLIS);
    }

    public boolean isCookieExpired(){
        return (cookieExpirationDate.getTime() - System.currentTimeMillis()<0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SecretKeyWrapper that = (SecretKeyWrapper) o;

        if (cookieSecretKey != null ? !cookieSecretKey.equals(that.cookieSecretKey) : that.cookieSecretKey != null)
            return false;
        return cookieExpirationDate != null ? cookieExpirationDate.equals(that.cookieExpirationDate) : that.cookieExpirationDate == null;

    }

    @Override
    public int hashCode() {
        int result = cookieSecretKey != null ? cookieSecretKey.hashCode() : 0;
        result = 31 * result + (cookieExpirationDate != null ? cookieExpirationDate.hashCode() : 0);
        return result;
    }
}

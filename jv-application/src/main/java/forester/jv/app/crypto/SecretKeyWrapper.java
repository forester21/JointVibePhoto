package forester.jv.app.crypto;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * Created by FORESTER on 13.12.17.
 */
public class SecretKeyWrapper {

    private SecretKey cookieSecretKey;
    private Date cookieExpirationDate;

    public SecretKeyWrapper(SecretKey cookieSecretKey, Date cookieExpirationDate) {
        this.cookieSecretKey = cookieSecretKey;
        this.cookieExpirationDate = cookieExpirationDate;
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

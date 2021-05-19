
public interface IItIsTime {

    // ------------------------------------------------------------
    /**
     * Send the message "10" + tp + grp + key and receive the server's reply.
     * The server's reply may be:
     * - "20" + login => return the login name
     * - "50", "51", "55" => print an error message and return null
     */
    public String connect(String tp, String grp, String key);

    // ------------------------------------------------------------
    /**
     * Send the message "12" and receive the server's reply:
     * - "52"  => print an error message and return -1
     * - "22" + nb => connect to this remote port, transmit the file and
     *   close. Send the message "13" and receive the server's reply:
     *    - "23" + size => return size
     *    - "53", "55" => print an error message and return -1
     */
    public int send(String filename);

    // ------------------------------------------------------------
    /**
     * Send the message "14" and receive the server's reply:
     * - "24" => return 
     * - "55" => print an error message and return 
     */
    public void close();

}

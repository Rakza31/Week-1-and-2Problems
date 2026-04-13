//DNS Cache with TTL (Time To Live)
import java.util.*;

class DNSEntry {

    String ip;
    long expiry;

    DNSEntry(String ip, int ttlSeconds) {
        this.ip = ip;
        this.expiry = System.currentTimeMillis() + ttlSeconds * 1000;
    }
}

public class DNSCache {

    HashMap<String, DNSEntry> cache = new HashMap<>();

    public String resolve(String domain) {

        DNSEntry entry = cache.get(domain);

        if (entry != null && entry.expiry > System.currentTimeMillis()) {
            System.out.println("Cache HIT");
            return entry.ip;
        }

        System.out.println("Cache MISS");

        String ip = queryDNS(domain);
        cache.put(domain, new DNSEntry(ip, 10));

        return ip;
    }

    private String queryDNS(String domain) {
        return "172.217.14.206";
    }

    public static void main(String[] args) {

        DNSCache dns = new DNSCache();

        System.out.println(dns.resolve("google.com"));
        System.out.println(dns.resolve("google.com"));
    }
}

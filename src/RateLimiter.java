//Distributed Rate Limiter for API Gateway
import java.util.*;

class TokenBucket {

    int tokens;
    int maxTokens;
    long lastRefill;
    int refillRate;

    TokenBucket(int maxTokens, int refillRate) {
        this.maxTokens = maxTokens;
        this.refillRate = refillRate;
        this.tokens = maxTokens;
        this.lastRefill = System.currentTimeMillis();
    }

    synchronized boolean allowRequest() {

        long now = System.currentTimeMillis();
        long elapsed = (now - lastRefill) / 1000;

        tokens = Math.min(maxTokens,
                tokens + (int) (elapsed * refillRate));

        lastRefill = now;

        if (tokens > 0) {
            tokens--;
            return true;
        }
        return false;
    }
}

public class RateLimiter {

    HashMap<String, TokenBucket> clients = new HashMap<>();

    public boolean check(String clientId) {

        clients.putIfAbsent(clientId,
                new TokenBucket(5, 1));

        return clients.get(clientId).allowRequest();
    }

    public static void main(String[] args) {

        RateLimiter limiter = new RateLimiter();

        for (int i = 0; i < 10; i++) {

            boolean allowed = limiter.check("client1");

            System.out.println("Request " + i + ": " + allowed);
        }
    }
}

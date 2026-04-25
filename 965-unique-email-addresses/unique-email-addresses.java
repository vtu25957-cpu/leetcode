import java.util.HashSet;
import java.util.Set;

class Solution {
    public int numUniqueEmails(String[] emails) {
        // Set to store unique "actual" email addresses
        Set<String> uniqueEmails = new HashSet<>();
        
        for (String email : emails) {
            // 1. Split into local and domain parts
            int atIndex = email.indexOf('@');
            String local = email.substring(0, atIndex);
            String domain = email.substring(atIndex); // Keep the '@' with the domain
            
            // 2. Handle the '+' rule: ignore everything after '+'
            int plusIndex = local.indexOf('+');
            if (plusIndex != -1) {
                local = local.substring(0, plusIndex);
            }
            
            // 3. Handle the '.' rule: remove all periods
            local = local.replace(".", "");
            
            // 4. Combine and add to the set
            uniqueEmails.add(local + domain);
        }
        
        return uniqueEmails.size();
    }
}
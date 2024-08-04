import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Voter {
    private String id;
    private String name;
    private boolean hasVoted;

    public Voter(String id, String name) {
        this.id = id;
        this.name = name;
        this.hasVoted = false;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean hasVoted() {
        return hasVoted;
    }

    public void setHasVoted(boolean hasVoted) {
        this.hasVoted = hasVoted;
    }
}

class Candidate {
    private String id;
    private String name;
    private int voteCount;

    public Candidate(String id, String name) {
        this.id = id;
        this.name = name;
        this.voteCount = 0;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void incrementVoteCount() {
        this.voteCount++;
    }
}

public class OnlineVotingSystem {
    private Map<String, Voter> voters;
    private Map<String, Candidate> candidates;
    private Scanner scanner;

    public OnlineVotingSystem() {
        this.voters = new HashMap<>();
        this.candidates = new HashMap<>();
        this.scanner = new Scanner(System.in);

        // Initialize some voters and candidates for testing
        voters.put("V1", new Voter("V1", "ram"));
        voters.put("V2", new Voter("V2", "sham"));
        candidates.put("C1", new Candidate("C1", "Candidate 1"));
        candidates.put("C2", new Candidate("C2", "Candidate 2"));
    }

    public void run() {
        while (true) {
            System.out.println("1. Voter login");
            System.out.println("2. View candidates");
            System.out.println("3. View results");
            System.out.println("4. Exit");
            System.out.println("Please select an option:");
            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    voterLogin();
                    break;
                case 2:
                    viewCandidates();
                    break;
                case 3:
                    viewResults();
                    break;
                case 4:
                    System.out.println("Thank you for using the online voting system!");
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private void voterLogin() {
        System.out.println("Please enter your voter ID:");
        String voterId = scanner.nextLine();

        if (!voters.containsKey(voterId)) {
            System.out.println("Voter not found!");
            return;
        }

        Voter voter = voters.get(voterId);

        if (voter.hasVoted()) {
            System.out.println("You have already voted!");
            return;
        }

        System.out.println("Welcome, " + voter.getName() + "!");
        castVote(voter);
    }

    private void castVote(Voter voter) {
        System.out.println("Please select a candidate to vote for:");
        for (Candidate candidate : candidates.values()) {
            System.out.println(candidate.getId() + ". " + candidate.getName());
        }

        String candidateId = scanner.nextLine();

        if (!candidates.containsKey(candidateId)) {
            System.out.println("Candidate not found!");
            return;
        }

        Candidate candidate = candidates.get(candidateId);
        candidate.incrementVoteCount();
        voter.setHasVoted(true);
        System.out.println("Vote cast successfully!");
    }

    private void viewCandidates() {
        System.out.println("List of candidates:");
        for (Candidate candidate : candidates.values()) {
            System.out.println(candidate.getId() + ". " + candidate.getName());
        }
    }

    private void viewResults() {
        System.out.println("Voting results:");
        for (Candidate candidate : candidates.values()) {
            System.out.println(candidate.getName() + ": " + candidate.getVoteCount() + " votes");
        }
    }

    public static void main(String[] args) {
        OnlineVotingSystem onlineVotingSystem = new OnlineVotingSystem();
        onlineVotingSystem.run();
    }
}

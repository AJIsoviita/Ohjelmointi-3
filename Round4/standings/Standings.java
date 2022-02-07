import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


class Standings{

    private ArrayList<Team> teams;

    Standings(){
        teams = new ArrayList<Team>();
    }

    Standings(String filename) throws FileNotFoundException, IOException{

        teams = new ArrayList<Team>();
        readMatchData(filename);
    }

    public void readMatchData(String filename) throws FileNotFoundException, IOException{
        try(var file = new BufferedReader(new FileReader(filename))) {
            String standings;

            while((standings = file.readLine()) != null) {
                String[]split = standings.split("\\t");

                String teamname1 = split[0];
                String teamname2 = split[2];

                String[]scores = split[1].split("-");

                int team1goals = Integer.parseInt(scores[0]);
                int team2goals = Integer.parseInt(scores[1]);

                addMatchResult(teamname1, team1goals, team2goals, teamname2);
        }
    }
    }

    public void addMatchResult(String teamNameA, int goalsA, int goalsB, String teamNameB){
        Integer team1index = null;
        Integer team2index = null;

        // katsotaan olemassa olevat tiimit
        for (int i = 0; i < teams.size(); i++){
            if(teamNameA.equals(teams.get(i).getName())){         
                team1index = i;
            }
            if(teamNameB.equals(teams.get(i).getName())){
                team2index = i;
            } 
        }
        //tarkistetaan voittaja ja lasketaan pisteet
        Boolean teamAwin = false;
        Boolean gameTie = false;

        if(goalsA > goalsB){
            teamAwin = true;
        }
        if(goalsA == goalsB){
            gameTie = true;
        }

        if (team1index!= null){
            teams.get(team1index).scored += goalsA;
            teams.get(team1index).allowed += goalsB;

            if (gameTie){
                teams.get(team1index).ties += 1;
                teams.get(team1index).points += 1;
            }
            if (teamAwin && !gameTie){
                teams.get(team1index).wins += 1;
                teams.get(team1index).points += 3;
            }
            if (!teamAwin && !gameTie){
                teams.get(team1index).losses += 1;
            }
        }
        else{
            Team team1 = new Team(teamNameA);
            team1.scored += goalsA;
            team1.allowed += goalsB;
            
            if (gameTie){
                team1.ties += 1;
                team1.points += 1;
            }
            if (teamAwin && !gameTie){
                team1.wins += 1;
                team1.points += 3;
            }
            if (!teamAwin && !gameTie){
                team1.losses += 1;
            }
            teams.add(team1);
        }

        if (team2index!=null){
            teams.get(team2index).scored += goalsB;
            teams.get(team2index).allowed += goalsA;

            if (gameTie){
                teams.get(team2index).ties += 1;
                teams.get(team2index).points += 1;
            }
            if (!teamAwin && !gameTie){
                teams.get(team2index).wins += 1;
                teams.get(team2index).points += 3;
            }
            if (teamAwin && !gameTie){
                teams.get(team2index).losses += 1;
            }
        }
        else{
            Team team2 = new Team(teamNameB);
            team2.scored += goalsB;
            team2.allowed += goalsA;
            
            if (gameTie){
                team2.ties += 1;
                team2.points += 1;
            }
            if (!teamAwin && !gameTie){
                team2.wins += 1;
                team2.points += 3;
            }
            if (teamAwin && !gameTie){
                team2.losses += 1;
            }

            teams.add(team2);
        }  
    }

    public ArrayList<Team> getTeams(){

        Collections.sort(teams,(o1, o2) -> {
            if (o1.getPoints() == (o2.getPoints())) {
                if((o2.getScored()-o2.allowed) == (o1.getScored()-o1.allowed)){
                    if(o2.getScored() == o1.getScored()){
                        return o2.getName().compareTo(o1.getName());
                    }
                    else{
                        return Integer.compare(o2.getScored(),o1.getScored());
                    }
                }
                else{
                    return Integer.compare((o2.getScored()-o2.allowed),(o1.getScored()-o1.allowed));
                }
            } 
            else {
              return Integer.compare(o2.getPoints(),o1.getPoints());
            }
        });
        return teams;
    }

    public void printStandings(){
        
        Collections.sort(teams,(o1, o2) -> {
            if (o1.getPoints() == (o2.getPoints())) {
                if((o2.getScored()-o2.allowed) == (o1.getScored()-o1.allowed)){
                    if(o2.getScored() == o1.getScored()){
                        return o2.getName().compareTo(o1.getName());
                    }
                    else{
                        return Integer.compare(o2.getScored(),o1.getScored());
                    }
                }
                else{
                    return Integer.compare((o2.getScored()-o2.allowed),(o1.getScored()-o1.allowed));
                }
            } 
            else {
              return Integer.compare(o2.getPoints(),o1.getPoints());
            }
        });
        //haetaan isoin merkkijono nimistä

        int longboi = 0;
        for (Team item : teams){
            if(item.getName().length() > longboi){
                longboi = item.getName().length();
            }
        }

        for (int i = 0; i < teams.size(); i++){
            Team currentTeam = teams.get(i);
            int gamesPlayed = currentTeam.wins + currentTeam.losses + currentTeam.ties;

            String scoreAllowRatio = String.valueOf(currentTeam.getScored())+
            "-"+String.valueOf(currentTeam.getAllowed());

            System.out.printf("%-"+longboi+"s %3d %3d %3d %3d %6s %3d%n", 
            currentTeam.getName(),
            gamesPlayed,
            currentTeam.getWins(),
            currentTeam.getTies(),
            currentTeam.getLosses(),
            scoreAllowRatio,
            currentTeam.getPoints()
            );
        }
    }

    public static class Team{

        private int wins = 0;
        private int ties = 0;
        private int losses = 0;
        private int scored = 0;
        private int allowed = 0;
        private int points = 0;
        private String team_name;

        public Team(String name){
            team_name = name;
        }
        public String getName(){return team_name;}

        public int getWins(){return wins;}
        public int getTies(){return ties;}
        public int getLosses(){return losses;}
        public int getScored(){return scored;}
        public int getAllowed(){return allowed;}
        public int getPoints(){return points;}

    }

}
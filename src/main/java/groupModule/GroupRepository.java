package groupModule;

import java.util.*;

public class GroupRepository {

    public final Map<String,Group> groups = new LinkedHashMap<>();

    public GroupRepository(List<Group> groups){
        if (groups != null){
            for (Group group: groups){
                this.groups.put(group.getGroupNumber(),group);
            }
        }
    }

    public Group findGroupById(String id){
        for (Group group: groups.values()){
            if (group.getGroupNumber().equals(id)){
                return group;
            }
        }
        return null;
    }

    public List<Group> findAll() {
        return new ArrayList<>(groups.values());
    }

    public void create(Group group){
        String id = UUID.randomUUID().toString();
        group.setGroupNumber(id);
        groups.put(id,group);
    }

    public void delete(String id){
        groups.remove(id);
    }

    public Group update(Group group){
        groups.put(group.getGroupNumber(),group);
        return group;
    }

}

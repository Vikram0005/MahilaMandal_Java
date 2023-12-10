package com.mahilamandal.response;

import com.mahilamandal.response.info.GroupInfo;
import lombok.Data;
import java.util.List;

@Data
public class GroupResponse {
    private List<GroupInfo> groups;
}

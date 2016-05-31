package net.gongmingqm10.zhihu.network.data;

import net.gongmingqm10.zhihu.model.Theme;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThemeResponse {
    private int limit;
    private List<Theme> others;
}

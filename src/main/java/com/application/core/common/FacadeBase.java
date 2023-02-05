package com.application.core.common;

import com.application.core.util.EnvironmentUtil;
import com.application.dto.mapper.GenericMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class FacadeBase {

    @Autowired
    public GenericMapper genericMapper;

}

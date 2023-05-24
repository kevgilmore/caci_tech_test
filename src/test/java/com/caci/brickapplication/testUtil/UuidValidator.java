package com.caci.brickapplication.testUtil;

import java.util.regex.Pattern;

public class UuidValidator {
    public static Pattern UUID_REGEX =
            Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
}

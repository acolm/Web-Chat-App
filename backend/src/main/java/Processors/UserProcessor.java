package Processors;

import Responses.Response;
import User.UserDTO;

public interface UserProcessor {

    Response process(UserDTO user);
}
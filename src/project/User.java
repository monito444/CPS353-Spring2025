package project;

import project.annotations.NetworkAPI;

@NetworkAPI
public interface User{
	ComputeResult compute(ComputeRequest request);
}
---
layout: default
title: Usage
---

## Using Component Bindings Provider

The Component Bindings Provider allows developers to avoid scriptlet in JSPs by defining a Component Bindings Provider which will be invoked before a component JSP is executed.

### Creating a Component Bindings Provider Service 

In order to create a Component Bindings Provider, you simply extend the ComponentBindingsProvider interface.  For example:

	package com.mycompany.services.impl;
	
	import javax.servlet.jsp.PageContext;
	
	import org.apache.felix.scr.annotations.Component;
	import org.apache.felix.scr.annotations.Properties;
	import org.apache.felix.scr.annotations.Property;
	import org.apache.felix.scr.annotations.Service;
	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;
	
	import com.sixdimensions.wcm.cq.component.bindings.api.CQVariables;
	import com.sixdimensions.wcm.cq.component.bindings.api.ComponentBindingsProvider;

	@Service(value = ComponentBindingsProvider.class)
	@Component(name = "com.mycompany.services.impl.ExampleComponentBindingsProvider", label = "Example Component Bindings Provider", description = "Initalizes the example components")
	@Properties({ @Property(name = ComponentBindingsProvider.RESOURCE_TYPE_PROP, value = {
		"myapp/components/example",
		"myapp/components/mycomponent2" }) })
	public class ExampleComponentBindingsProvider implements ComponentBindingsProvider {

		private static final Logger log = LoggerFactory
			.getLogger(ExampleComponentBindingsProvider.class);

		@Override
		public void initialize(CQVariables variables, Bindings bindings) {
			log.trace("initialize");
			bindings.put("title", variables.getCurrentPage().getTitle());
		}
	}


The CQVariables object which gets passed into the Component Bindings Provider is essentially a map of all of the objects CQ binds into the page context with named getters for each one.

As you can see in the example one ore more Component Bindings Provider can be associated with a single resource type and a Component Initializer can be associated with one or more resource types.

### Using the Bound Variables

The variables set in the Component Bindings Provider can then be accessed from the bindings object, for example to access a variable called 'title' you would use this expression:

	${bindings.title}
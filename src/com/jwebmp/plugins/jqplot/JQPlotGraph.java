/*
 * Copyright (C) 2017 Marc Magon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.jwebmp.plugins.jqplot;

import com.jwebmp.Component;
import com.jwebmp.base.html.attributes.NoAttributes;
import com.jwebmp.base.html.interfaces.DisplayObjectType;
import com.jwebmp.base.html.interfaces.GlobalChildren;
import com.jwebmp.base.html.interfaces.NoNewLineBeforeClosingTag;
import com.jwebmp.base.html.interfaces.children.NoChildren;
import com.jwebmp.base.servlets.enumarations.ComponentTypes;
import com.jwebmp.htmlbuilder.javascript.JavaScriptPart;
import com.jwebmp.plugins.jqplot.options.JQPlotOptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class incorporates the JQPlot library from http://www.jqplot.com
 *
 * @param <O>
 * @param <J>
 *
 * @author mmagon
 * @version 3.0
 * 		<p>
 * 		Changes to how the Renderering and options work together
 * 		<p>
 * 		3.0 2016/02/28 Implementation into Options interface with JavaScriptFunctions as the base
 * @since 2014/07/08
 */
public abstract class JQPlotGraph<O extends JavaScriptPart, J extends JQPlotGraph<O, J>>
		extends Component<NoChildren, NoAttributes, JQPlotGraphFeatures, JQPlotGraphEvents, J>
		implements GlobalChildren, DisplayObjectType, NoNewLineBeforeClosingTag
{

	/**
	 * Version 2
	 */
	private static final long serialVersionUID = 2L;
	/**
	 * All the data points
	 */
	private final List<Serializable> dataObjects = new ArrayList<>();

	private JQPlotGraphFeature feature;
	private JQPlotOptions options;

	protected JQPlotGraph()
	{
		super(ComponentTypes.Div);
		addFeature(getFeature());
	}

	/**
	 * Gets the feature for the graph. Please don't ever return a null. JQPlotGraphFeature is also fine
	 * <p>
	 *
	 * @return
	 */
	public JQPlotGraphFeature getFeature()
	{
		if (feature == null)
		{
			feature = new JQPlotGraphFeature(this);
		}
		return feature;
	}

	/**
	 * Sets the feature for this graph
	 *
	 * @param feature
	 */
	public void setFeature(JQPlotGraphFeature feature)
	{
		this.feature = feature;
	}

	/**
	 * Renders the data point section on the graph
	 * <p>
	 *
	 * @return The Data Points String
	 */
	protected abstract StringBuilder getDataPointRender();

	/**
	 * Returns the graph options
	 * <p>
	 *
	 * @return
	 */
	@Override
	public JQPlotOptions<?> getOptions()
	{
		if (options == null)
		{
			options = new JQPlotOptions<>(this);
			getFeature().setOptions(options);
		}
		return options;
	}

	/**
	 * Sets the options for this graph
	 *
	 * @param options
	 */
	public void setOptions(JQPlotOptions options)
	{
		this.options = options;
	}

	/**
	 * Returns the list of data objects currently in the graph
	 *
	 * @return
	 */
	public List<Serializable> getDataObjects()
	{
		return dataObjects;
	}

	@Override
	public boolean equals(Object o)
	{
		return super.equals(o);
	}

	@Override
	public int hashCode()
	{
		return super.hashCode();
	}
}
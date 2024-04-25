/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package com.xwiki.confluencepro.internal.converters;

import org.apache.commons.lang3.StringUtils;
import org.xwiki.component.annotation.Component;
import org.xwiki.contrib.confluence.filter.internal.input.ConfluenceConverter;
import org.xwiki.contrib.confluence.filter.internal.macros.AbstractMacroConverter;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Map;

/**
 * Pagetreesearch Macro Converter - converts pagetreesearch to the Location Search Macro.
 *
 * See:
 * - https://support.atlassian.com/confluence-cloud/docs/insert-the-page-tree-search-macro/
 * - https://extensions.xwiki.org/xwiki/bin/view/Extension/Location%20Search%20Macro
 *
 * @version $Id$
 * @since 1.18.0
 */
@Component
@Singleton
@Named("pagetreesearch")
public class PagetreesearchMacroConverter extends AbstractMacroConverter
{
    @Inject
    private ConfluenceConverter converter;

    @Override
    protected String toXWikiId(String confluenceId, Map<String, String> confluenceParameters, String confluenceContent,
        boolean inline)
    {
        return "locationSearch";
    }

    @Override
    protected Map<String, String> toXWikiParameters(String confluenceId, Map<String, String> confluenceParameters,
        String content)
    {
        String root = confluenceParameters.get("rootPage");
        if (StringUtils.isEmpty(root)) {
            root = "@self";
        }
        return Map.of("reference", converter.convertDocumentReference(null, root));
    }
}

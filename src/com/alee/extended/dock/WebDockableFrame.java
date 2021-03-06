/*
 * This file is part of WebLookAndFeel library.
 *
 * WebLookAndFeel library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * WebLookAndFeel library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with WebLookAndFeel library.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.alee.extended.dock;

import com.alee.extended.painter.Painter;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.utils.TextUtils;

import javax.swing.*;
import java.awt.*;

/**
 * User: mgarin Date: 28.06.12 Time: 13:31
 */

public class WebDockableFrame extends WebPanel
{
    private static final ImageIcon dockTop = new ImageIcon ( WebDockablePane.class.getResource ( "icons/dock_top.png" ) );
    private static final ImageIcon dockLeft = new ImageIcon ( WebDockablePane.class.getResource ( "icons/dock_left.png" ) );
    private static final ImageIcon dockRight = new ImageIcon ( WebDockablePane.class.getResource ( "icons/dock_right.png" ) );
    private static final ImageIcon dockBottom = new ImageIcon ( WebDockablePane.class.getResource ( "icons/dock_bottom.png" ) );

    public static final String ID_PREFIX = "WDF";

    private String frameId;
    private FrameType frameType;

    private final WebPanel titlePanel;
    private final WebLabel titleLabel;
    private final WebButton dock;

    public WebDockableFrame ()
    {
        this ( "" );
    }

    public WebDockableFrame ( final String frameTitle )
    {
        this ( TextUtils.generateId ( ID_PREFIX ), frameTitle );
    }

    public WebDockableFrame ( final String frameId, final String frameTitle )
    {
        this ( frameId, null, frameTitle );
    }

    public WebDockableFrame ( final Icon frameIcon, final String frameTitle )
    {
        this ( TextUtils.generateId ( ID_PREFIX ), frameIcon, frameTitle );
    }

    public WebDockableFrame ( final String frameId, final Icon frameIcon, final String frameTitle )
    {
        super ( true );

        this.frameId = frameId;

        setShadeWidth ( 0 );
        setWebColoredBackground ( false );
        setPaintSides ( false, false, false, false );

        titlePanel = new WebPanel ( true )
        {
            @Override
            protected void paintComponent ( final Graphics g )
            {
                super.paintComponent ( g );
                g.setColor ( Color.WHITE );
                g.drawLine ( 0, 0, 0, getHeight () - 2 );
                g.drawLine ( 0, 0, getWidth () - 1, 0 );
                g.drawLine ( getWidth () - 1, 0, getWidth () - 1, getHeight () - 2 );
            }
        };
        titlePanel.setPaintSides ( false, false, true, false );
        titlePanel.setShadeWidth ( 0 );
        add ( titlePanel, BorderLayout.NORTH );

        titleLabel = new WebLabel ( frameTitle, frameIcon );
        titleLabel.setMargin ( 3, 3, 3, 20 );
        titleLabel.setDrawShade ( true );
        titlePanel.add ( titleLabel, BorderLayout.CENTER );

        dock = new WebButton ();
        dock.setLeftRightSpacing ( 0 );
        dock.setShadeWidth ( 0 );
        dock.setDrawSides ( false, true, false, false );
        titlePanel.add ( dock, BorderLayout.EAST );
    }

    public String getFrameId ()
    {
        return frameId;
    }

    public void setFrameId ( final String frameId )
    {
        this.frameId = frameId;
    }

    public void setIcon ( final Icon icon )
    {
        titleLabel.setIcon ( icon );
    }

    public Icon getIcon ()
    {
        return titleLabel.getIcon ();
    }

    public FrameType getFrameType ()
    {
        return frameType;
    }

    public void setFrameType ( final FrameType frameType )
    {
        this.frameType = frameType;

        // Changing displayed sides
        setPaintSides ( frameType.equals ( FrameType.bottom ), frameType.equals ( FrameType.right ), frameType.equals ( FrameType.top ),
                frameType.equals ( FrameType.left ) );

        // Changing tool icons
        dock.setIcon ( getDockIcon ( frameType ) );
    }

    public void setTitlePainter ( final Painter painter )
    {
        titlePanel.setPainter ( painter );
    }

    public Painter getTitlePainter ()
    {
        return titlePanel.getPainter ();
    }

    public WebPanel getTitlePanel ()
    {
        return titlePanel;
    }

    public WebLabel getTitleLabel ()
    {
        return titleLabel;
    }

    private Icon getDockIcon ( final FrameType frameType )
    {
        if ( frameType.equals ( FrameType.top ) )
        {
            return dockTop;
        }
        else if ( frameType.equals ( FrameType.left ) )
        {
            return dockLeft;
        }
        else if ( frameType.equals ( FrameType.right ) )
        {
            return dockRight;
        }
        else if ( frameType.equals ( FrameType.bottom ) )
        {
            return dockBottom;
        }
        else
        {
            return null;
        }
    }
}
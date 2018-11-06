/*
 * This file is part of Dynamic Surroundings, licensed under the MIT License (MIT).
 *
 * Copyright (c) OreCruncher
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.orecruncher.dsurround.client.handlers;

import javax.annotation.Nonnull;

import org.orecruncher.dsurround.client.handlers.EnvironStateHandler.EnvironState;
import org.orecruncher.dsurround.entity.CapabilityEntityData;
import org.orecruncher.dsurround.entity.data.IEntityDataSettable;
import org.orecruncher.dsurround.event.EntityDataEvent;
import org.orecruncher.lib.WorldUtils;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityDataSyncHandler extends EffectHandlerBase {

	public EntityDataSyncHandler() {
		super("Entity Data Sync");
	}

	@SubscribeEvent
	public void onEntityEmojiEvent(@Nonnull final EntityDataEvent event) {
		final Entity entity = WorldUtils.locateEntity(EnvironState.getWorld(), event.entityId);
		if (entity != null) {
			final IEntityDataSettable data = (IEntityDataSettable) CapabilityEntityData.getCapability(entity);
			if (data != null) {
				data.setAttacking(event.isAttacking);
				data.setFleeing(event.isFleeing);
			}
		}
	}

}

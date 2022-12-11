package com.expertmode.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

public class ExpertModeSavedData extends SavedData {
	
	private boolean expertMode = true;
	private ListTag list = new ListTag();
	
	public static ExpertModeSavedData create() {
		
		return new ExpertModeSavedData();
		
	}
	
	@Override
	public CompoundTag save(CompoundTag tag) {
		
		tag.putBoolean("expertmode", expertMode);
		tag.put("list", list);
		
		return tag;
		
	}
	
	public static ExpertModeSavedData load(CompoundTag tag) {
		
		ExpertModeSavedData data = create();

		data.expertMode = tag.getBoolean("expertmode");
		data.list = (ListTag) tag.get("list");
		
		return data;
		
	}
	
	public static ExpertModeSavedData get(ServerLevel level) {
		
		return level.getDataStorage().computeIfAbsent(ExpertModeSavedData::load, ExpertModeSavedData::create, "expertmode");
		
	}
	
	public void setExpertMode(boolean bool) {
		
		this.expertMode = bool;
		this.setDirty();
		
	}
	
	public boolean getExpertMode(ExpertModeSavedData data) {
		
		return data.expertMode;
		
	}
	
	public void setSetting(String string, Boolean bool) {
		
		CompoundTag tag1 = new CompoundTag();
		for(int i = 0; i < list.size(); i++) {
			
			if(list.getCompound(i).contains(string)) {
				
				list.remove(i);
				
			}
			
		}
		tag1.putBoolean(string, bool);
		list.add(tag1);
		
		this.setDirty();
		
	}
	
	public boolean getSetting(ExpertModeSavedData data, String string) {

		for(int i = 0; i < list.size(); i++) {
			
			if(list.getCompound(i).contains(string)) {
				
				return list.getCompound(i).getBoolean(string);
				
			}
			
		}
		return false;
		
	}
	
	public ListTag getList(ExpertModeSavedData data) {
		
		return data.list;
		
	}

}
